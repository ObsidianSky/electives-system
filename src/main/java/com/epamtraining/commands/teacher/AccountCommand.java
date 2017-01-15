package com.epamtraining.commands.teacher;

import com.epamtraining.commands.TeacherCommand;
import com.epamtraining.dao.DaoFactory;
import com.epamtraining.entities.Account;
import com.epamtraining.entities.Course;
import com.epamtraining.entities.Rating;
import com.epamtraining.exception.*;
import com.epamtraining.service.AuthenticationService;
import com.epamtraining.service.CoursesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Teacher page command
 * @author Sergey Bondarenko
 */
public class AccountCommand extends TeacherCommand {
    /**
     * Displays list of courses for teacher
     * @param request request to read the command from
     * @param response
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        Account account = AuthenticationService.account(request);

        try {
            CoursesService.setCoursesForTeacher(request, account);
        } catch (ServiceLogicalException | ServiceTechnicalException e) {
            throw new CommandException(e);
        }

        return pathManager.getString("path.page.teacher.account");
    }
}
