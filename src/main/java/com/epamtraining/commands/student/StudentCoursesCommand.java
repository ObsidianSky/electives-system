package com.epamtraining.commands.student;

import com.epamtraining.commands.StudentCommand;
import com.epamtraining.entities.Account;
import com.epamtraining.exception.*;
import com.epamtraining.service.AuthenticationService;
import com.epamtraining.service.CoursesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Student page command
 * @author Sergey Bondarenko
 */
public class StudentCoursesCommand extends StudentCommand {
    /**
     * Command for student courses list. Displays active student courses.
     * @param request request to read the command from
     * @param response
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        Account account = AuthenticationService.account(request);

        try {
            CoursesService.setCoursesWithRatingsForStudent(request, account);
        } catch (ServiceLogicalException | ServiceTechnicalException e) {
            throw new CommandException(e);
        }
        return pathManager.getString("path.page.student.account");
    }
}

