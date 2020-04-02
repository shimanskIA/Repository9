package l9tag;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import l9e.User;
import l9e.UserList;
import l9e.UserList.UserExistsException;
import l9h.UserListHelper;

public class AddUser extends SimpleTagSupport
{
    private User user;
    private boolean first = true;

    public void setUser(User user)
    {
        this.user = user;
    }

    public void doTag() throws JspException, IOException
    {
        if (first == true)
        {
            ArrayList<String> answers = new ArrayList<String>();
            answers.add("smwm");
            answers.add("MY5N5");
            answers.add("qGphJ");
            answers.add("iMKiZ");
            answers.add("fdamc");
            first = false;
            getJspContext().setAttribute("answersList", answers, PageContext.SESSION_SCOPE);
        }

        String errorMessage = null;

        UserList userList = (UserList) getJspContext().getAttribute("users", PageContext.APPLICATION_SCOPE);

        if (user.getLogin() == null || user.getLogin().equals(""))
        {
            errorMessage = "Логин не может быть пустым!";
        }
        else {
            if (user.getName() == null || user.getName().equals(""))
            {
                errorMessage = "Имя пользователя не может быть пустым!";
            }
            else
            {
                if (user.getCaptcha() == null || user.getCaptcha().equals(""))
                {
                    errorMessage = "Пройдите тест на трансформера!";
                }
            }
        }

        if (errorMessage == null)
        {
            int number = Integer.parseInt(getJspContext().getAttribute("chosenPicture", PageContext.SESSION_SCOPE).toString().substring(0, 1)) - 1;
            ArrayList<String> newanswers = (ArrayList<String>)getJspContext().getAttribute("answersList", PageContext.SESSION_SCOPE);
            if (!user.getCaptcha().equals(newanswers.get(number)))
            {
                errorMessage = "Похоже, что вы трансформер...";
                getJspContext().setAttribute("chosenPicture", Integer.toString(((int)Math.floor(Math.random() * 5)) + 1) + ".png", PageContext.SESSION_SCOPE);
            }
            else {
                try {
                    userList.addUser(user);
                    UserListHelper.saveUserList(userList);
                } catch (UserExistsException e) {
                    errorMessage = "Пользователь с таким логином уже существует!";
                }
            }
        }
        getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
    }
}