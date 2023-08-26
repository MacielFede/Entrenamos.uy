package controllers;

import dataTypes.DtUser;
import exceptions.AtributeAlreadyExists;
import exceptions.EmptyRequiredFieldException;
import exceptions.FebruaryDayException;
import exceptions.SameYearException;
import interfaces.UserInterface;
import services.ServiceFactory;
import services.UserService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class UserController implements UserInterface {
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private Map<String, DtUser> cachedUsers = null;

    public UserController() {
        super();
    }

    @Override
    public DtUser chooseUser(String nickname) {
        if (cachedUsers == null) {
            cachedUsers = userService.getAllUsers();
        }
        return cachedUsers.get(nickname);
    }

    @Override
    public String[] listUsersByNickname() {
        // Returns an array with all the user nicknames and the string "<Nicknames>"
        // Also renovates the cached array
        cachedUsers = userService.getAllUsers();
        List<String> nicknames = new ArrayList<>();
        nicknames.add("<Nicknames>");
        for (Map.Entry<String, DtUser> user : cachedUsers.entrySet()) {
            nicknames.add(user.getKey());
        }
        return nicknames.toArray(new String[0]);
    }

    @Override
    public void updateUserInfo(DtUser updatedUser) throws EmptyRequiredFieldException, FebruaryDayException, SameYearException {
        if (updatedUser.getBornDate().getYear() >= Calendar.getInstance().getWeekYear()) {
            throw new SameYearException();
        }
        if (updatedUser.getBornDate().getMonth() == 2 && Integer.parseInt(updatedUser.getBornDate().toString().substring(8, 10)) > 28) {
            throw new FebruaryDayException();
        }
        if (updatedUser.getEmail().isEmpty() || updatedUser.getNickname().isEmpty()) {
            String blankField = updatedUser.getEmail().isEmpty() ? updatedUser.getEmail() : updatedUser.getNickname();
            throw new EmptyRequiredFieldException(blankField);
        }
        userService.updateUser(updatedUser);
        cachedUsers.put(updatedUser.getNickname(), updatedUser);
    }

    @Override
    public void newMember(DtUser DtNewUser) {
        // TODO Auto-generated method stub

    }


    //=================================================================================================================
    // Se deberia crear una operacion en el UserService que devuelva lo que devuelve ListUsersByNickname.
    // Y mover esta operacion a UserService (que existsNickname este hecho a partir de UserService.ListUsersByNickname)
    //=================================================================================================================
    public boolean existsNickname(String nickName) {
        String[] allNickname = listUsersByNickname();
        for (String u : allNickname) {
            if (u.equals(nickName))
                return true;
        }
        return false;
    }

    public boolean existsEmail(String email) {
        String[] allEmail = userService.getAllEmails();
        for (String u : allEmail) {
            if (u.equals(email))
                return true;
        }
        return false;

    }

    public void newUser(DtUser newUser) throws EmptyRequiredFieldException, FebruaryDayException, SameYearException, AtributeAlreadyExists {
        if (newUser.getBornDate().getYear() >= Calendar.getInstance().getWeekYear())
            throw new SameYearException();

        if (newUser.getBornDate().getMonth() == 2 && Integer.parseInt(newUser.getBornDate().toString().substring(8, 10)) > 28)
            throw new FebruaryDayException();

        if (newUser.getEmail().isEmpty() || newUser.getNickname().isEmpty()) {
            String blankField = newUser.getEmail().isEmpty() ? newUser.getEmail() : newUser.getNickname();
            throw new EmptyRequiredFieldException(blankField);
        }
        if (existsNickname(newUser.getNickname()))
            throw new AtributeAlreadyExists((String) "Nickname", newUser.getNickname());

        if (existsEmail(newUser.getEmail()))
            throw new AtributeAlreadyExists("Email", newUser.getEmail());

        userService.newUser(newUser);
    }


}
