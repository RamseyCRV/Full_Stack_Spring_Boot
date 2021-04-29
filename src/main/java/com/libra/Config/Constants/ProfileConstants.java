package com.libra.Config.Constants;

public final class ProfileConstants {


    public final static String HTML = "profile";
    public final static String URL_PAGE = "/profile";
    public final static String URL_REDIRECT_TO_PAGE = "redirect:/profile";
    public final static String URL_EDIT_USER = "/profile/updateProfile";
    public final static String URL_DELETE_USER = "/profile/deleteAccount";
    public final static String URL_FIND_USER_BY_ID = "profile/findById";
    public final static String URL_EDIT_PASSWORD = "/profile/updatePassword";


    public final static String CRUD_SERVICE_QUALIFIER = "userServiceImpl";

    public final static String MODEL_USER = "activeUser";
    public final static String MODEL_COUNT_TODOS = "userTodos";
    public final static String MODEL_COUNT_NOTES = "userNotes";
    public final static String FLASH_ATTRIBUTE_PASSWORD_HOLDER = "passwordInfo";
    public final static String FLASH_ATTRIBUTE_PASSWORD_CHANGED = "The password was changed!";
    public final static String FLASH_ATTRIBUTE_PASSWORD_WRONG = "You entered a wrong current password! Try Again.";
}
