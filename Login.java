package l9tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import l9e.User;
import l9e.UserList;

public class Login extends SimpleTagSupport
{
    private String login;
    private String password;
    public void setLogin(String login)
    {
        this.login = login;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void doTag() throws JspException, IOException
    {
        String errorMessage = null;
        UserList userList = (UserList) getJspContext().getAttribute("users", PageContext.APPLICATION_SCOPE);
        if (login == null || login.equals(""))
        {
            errorMessage = "Логин не может быть пустым!";
        }
        else {
            User user = userList.findUser(login);
            if (user == null || !user.getPassword().equals(password))
            {
                errorMessage = "Такой пользователь не существует или указанная комбинация логин/пароль неверна!";
            }
            else {
                getJspContext().setAttribute("authUser", user, PageContext.SESSION_SCOPE);
            }
        }
        getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
    }
}