package l9s;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import l9e.Ad;
import l9e.AdList;
import l9e.UserList;
import l9h.AdListHelper;
import l9h.UserListHelper;

public class StartupServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        UserList userList = UserListHelper.readUserList(getServletContext());
        getServletContext().setAttribute("users", userList);
        AdList adList = AdListHelper.readAdList(getServletContext());
        getServletContext().setAttribute("ads", adList);

        for (Ad ad: adList.getAds())
        {
            ad.setAuthor(userList.findUser(ad.getAuthorId()));
            ad.setLastModified(ad.getLastModified());
        }
    }
}
