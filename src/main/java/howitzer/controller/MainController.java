package howitzer.controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import howitzer.beans.FireShot;
import howitzer.beans.HowitzerHistory;
import howitzer.beans.Logs;
import howitzer.beans.Users;
import howitzer.resources.HowitzerBundle;
import howitzer.service.HistoryService;
import howitzer.service.HowitzerService;
import howitzer.service.LogsService;
import howitzer.service.UserService;
import howitzer.util.ConnectionUtil;
import howitzer.util.PagingUtilities;

@Controller
public class MainController {

  @Resource(name = "howitzerService")
  private HowitzerService howitzerService;

  @Resource(name = "historyService")
  private HistoryService historyService;

  @Resource(name = "userService")
  private UserService userService;

  @Resource(name = "logsService")
  private LogsService logsService;

  final static Logger log = Logger.getLogger(MainController.class.getName());
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView login(@RequestParam(value = "error", required = false) String error,
      @RequestParam(value = "logout", required = false) String logout) {

    ModelAndView model = new ModelAndView();

    // If invalid login
    if (error != null) {

      model.addObject("error", "Invalid username and password!");

    }

    model.setViewName("login");
    return model;
    
  }

  @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    
    HttpSession session = request.getSession(false);
    SecurityContextHolder.clearContext();
    session = request.getSession(false);
    
    if (session != null) {
      
        session.invalidate();
        
    }
    
    for (Cookie cookie : request.getCookies()) {
      
        cookie.setMaxAge(0);
        
    }

    return "logout";
      
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView showMenu() {

    ModelAndView model = null;
    
    FireShot fireShot = null;

    // Users
    List<Users> users = new ArrayList<Users>();

    try (Connection conn = ConnectionUtil.getConnection();) {

    fireShot = new FireShot();
    
    // get users
    users = userService.getUsers(conn);
    
    model = new ModelAndView("menu", "fireShot", fireShot);
    model.addObject("selUserId", fireShot.getUserId());
    model.addObject("users", users);
    model.addObject("distance", fireShot.getDistanceToTarget());

    } catch (SQLException e) {

      log.error(e.getMessage(), e);

      model = new ModelAndView("menu", "fireShot", fireShot);
      model.addObject("users", users);
      model.addObject("msg", e);
      return model;

    }

    return model;

  }

  @RequestMapping(value = "/back", method = RequestMethod.GET)
  public ModelAndView backShowMenu() {

    return showMenu();

  }

  @RequestMapping(value = "/updateFireShot", method = RequestMethod.POST)
  public ModelAndView fireShot(HttpServletRequest req, FireShot fireShot) {
    
    FireShot fireShotResult = null;
    ModelAndView model = new ModelAndView("menu", "fireShot", fireShot);
    
    try (Connection conn = ConnectionUtil.getConnection();) {
      
      String userId = req.getParameter("userId");
      
      // Fire shot
      fireShotResult = howitzerService.fireShot(fireShot);
      
      // Rank users
      userService.rankUsers(conn);
      
      // Users
      List<Users> users = new ArrayList<Users>();

      // get users
      users = userService.getUsers(conn);

      model.addObject("selUserId", userId);
      model.addObject("users", users);
      model.addObject("distance", fireShot.getDistanceToTarget());
      model.addObject("angle", fireShot.getAngle());
      model.addObject("velocity", fireShot.getVelocity());
      model.addObject("targetSize", fireShot.getTargetSize());
      model.addObject("result", fireShotResult.getResult());
      model.addObject("distanceTraveled", fireShotResult.getDistanceTraveled());
      model.addObject("distanceMissedBy", fireShotResult.getDistanceMissedBy());
      model.addObject("rank", fireShotResult.getRank());
      model.addObject("trajectory", fireShotResult.getTrajectory());

    } catch (SQLException e) {
      
      e.printStackTrace();
      
    }
    
    return model;

  }

  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public ModelAndView getUsers(HttpServletRequest req, String msg) {

    ModelAndView model = null;

    try (Connection conn = ConnectionUtil.getConnection();) {

      // Get page size
      String pagSize = HowitzerBundle.getValueForKey("page.size");
      int pagSiz = Integer.parseInt(pagSize);

      // Get page up/page down
      String mv = req.getParameter("mv");

      // If no page up/page down
      if (mv == null) {

        mv = " ";

      }

      // Get first row (if back)
      String firstRow = req.getParameter("firstRow");

      // Get from row
      String fromRow = req.getParameter("fromRow");

      // Get to row
      String toRow = req.getParameter("toRow");

      // ArrayList to return fromRow and toRow
      ArrayList<String> alist1 = new ArrayList<String>(2);

      // Calculate from/to rows
      alist1 = PagingUtilities.calcFromToRows(pagSiz, mv, firstRow, fromRow, toRow);

      // Parse fromRow
      fromRow = alist1.get(0);
      int fRow = Integer.parseInt(fromRow);

      // Parse toRow
      toRow = alist1.get(1);
      // int tRow = Integer.parseInt(toRow);

      // Number of rows
      int nbrRows = userService.getCount(conn);

      List<Users> users = userService.getUsersRanksPaging(conn, fRow, pagSiz);

      model = new ModelAndView("listUsers");
      model.addObject("users", users);
      model.addObject("msg", msg);
      model.addObject("pagSize", pagSize);
      model.addObject("nbrRows", String.valueOf(nbrRows));
      model.addObject("fromRow", fromRow);
      model.addObject("toRow", toRow);

    } catch (SQLException e) {

      log.error(e.getMessage(), e);

      model = new ModelAndView("listUsers");
      model.addObject("msg", e);
      return model;

    }

    return model;

  }

  @RequestMapping(value = "/user", method = RequestMethod.GET)
  public ModelAndView showAddUser(HttpServletRequest req) {

    // Get first row (if back)
    String firstRow = req.getParameter("firstRow");

    ModelAndView model = new ModelAndView("addUsers", "user", new Users());
    model.addObject("firstRow", firstRow);

    return model;

  }

  @RequestMapping(value = "/addUser", method = RequestMethod.POST)
  public ModelAndView addUser(@ModelAttribute("user") Users user, HttpServletRequest req) {

    // Get first row
    String firstRow = req.getParameter("firstRow");

    ModelAndView model2 = null;

    String userId = null;
    int shots = 0;
    int hits = 0;
    int misses = 0;
    BigDecimal avgHits = BigDecimal.valueOf(0);
    int userRank = 0;

    int cntr = 0;

    String msg = "";

    StringBuffer strBuf = new StringBuffer();

    try (Connection conn = ConnectionUtil.getConnection();) {

      userId = user.getUserId();
      
      // Insert row
      cntr = userService.insertUser(conn, userId, shots, hits, misses, avgHits, userRank);
      
      // If user already exists
      if (cntr == 0) {

        strBuf.append("User id ").append(userId).append(" already exists.");

        msg = strBuf.toString();

        ModelAndView model3 = new ModelAndView("addUsers", "user", user);
        model3.addObject("firstRow", firstRow);
        model3.addObject("errorMsg", msg);
        return model3;

      // If user doesn't exist
      } else {

        strBuf.append("User id ").append(userId).append(" added.");

        msg = strBuf.toString();

      }

      model2 = getUsers(req, msg);
      model2.addObject("msg", msg);
      model2.addObject("firstRow", firstRow);

    } catch (SQLException e) {

      log.error(e.getMessage(), e);
      e.printStackTrace();

    }

    return model2;

  }

  @RequestMapping(value = "/history", method = RequestMethod.GET)
  public ModelAndView getHistory(HttpServletRequest req, String msg) {

    ModelAndView model = null;

    try (Connection conn = ConnectionUtil.getConnection();) {

      // Get page size
      String pagSize = HowitzerBundle.getValueForKey("page.size");
      int pagSiz = Integer.parseInt(pagSize);

      // Get page up/page down
      String mv = req.getParameter("mv");

      // If no page up/page down
      if (mv == null) {

        mv = " ";

      }

      // Get first row (if back)
      String firstRow = req.getParameter("firstRow");

      // Get from row
      String fromRow = req.getParameter("fromRow");

      // Get to row
      String toRow = req.getParameter("toRow");

      // Get search user id
      String schUserId = req.getParameter("schUserId");

      if (StringUtils.isEmpty(schUserId)) {

        schUserId = "";

      }

      // ArrayList to return fromRow and toRow
      ArrayList<String> alist1 = new ArrayList<String>(2);

      // Calculate from/to rows
      alist1 = PagingUtilities.calcFromToRows(pagSiz, mv, firstRow, fromRow, toRow);

      // Parse fromRow
      fromRow = alist1.get(0);
      int fRow = Integer.parseInt(fromRow);

      // Parse toRow
      toRow = alist1.get(1);
      // int tRow = Integer.parseInt(toRow);

      // Number of rows
      int nbrRows = historyService.getCount(conn, schUserId);

      List<HowitzerHistory> history = historyService.getHistory(conn, fRow, pagSiz, schUserId);

      model = new ModelAndView("listHistory");
      model.addObject("history", history);
      model.addObject("msg", msg);
      model.addObject("pagSize", pagSize);
      model.addObject("nbrRows", String.valueOf(nbrRows));
      model.addObject("fromRow", fromRow);
      model.addObject("toRow", toRow);
      model.addObject("schUserId", schUserId);

    } catch (SQLException e) {

      log.error(e.getMessage(), e);

      model = new ModelAndView("listHistory");
      model.addObject("msg", e);
      return model;

    }

    return model;

  }

  @RequestMapping(value = "/logs", method = RequestMethod.GET)
  public ModelAndView getLogs(HttpServletRequest req, String msg) {

    ModelAndView model = null;

    try (Connection conn = ConnectionUtil.getConnection();) {

      // Get page size
      String pagSize = HowitzerBundle.getValueForKey("errors.page.size");
      int pagSiz = Integer.parseInt(pagSize);

      // Get page up/page down
      String mv = req.getParameter("mv");

      // If no page up/page down
      if (mv == null) {

        mv = " ";

      }

      // Get first row (if back)
      String firstRow = req.getParameter("firstRow");

      // Get from row
      String fromRow = req.getParameter("fromRow");

      // Get to row
      String toRow = req.getParameter("toRow");

      // Get search text
      String schText = req.getParameter("schText");

      if (StringUtils.isEmpty(schText)) {

        schText = "";

      }

      // ArrayList to return fromRow and toRow
      ArrayList<String> alist1 = new ArrayList<String>(2);

      // Calculate from/to rows
      alist1 = PagingUtilities.calcFromToRows(pagSiz, mv, firstRow, fromRow, toRow);

      // Parse fromRow
      fromRow = alist1.get(0);
      int fRow = Integer.parseInt(fromRow);

      // Parse toRow
      toRow = alist1.get(1);
      //int tRow = Integer.parseInt(toRow);

      // Number of rows
      int nbrRows = logsService.getCount(conn, schText);

      List<Logs> logs = logsService.getLogs(conn, fRow, pagSiz, schText);

      model = new ModelAndView("listLogs");
      model.addObject("logs", logs);
      model.addObject("msg", msg);
      model.addObject("pagSize", pagSize);
      model.addObject("nbrRows", String.valueOf(nbrRows));
      model.addObject("fromRow", fromRow);
      model.addObject("toRow", toRow);
      model.addObject("schText", schText);

    } catch (SQLException e) {

      log.error(e.getMessage(), e);
      e.printStackTrace();

      model = new ModelAndView("listLogs");
      model.addObject("msg", e);
      return model;

    }

    return model;

  }

}
