package com.libra.Config;

/**
 * Custom constants
 */
public interface LibraConstants {

    /**
     * Configuration constants
     */
    interface ConfigConstants {
        String AVATAR_USER_PATH = "src/main/resources/static/images/avatars/";
        String PNG_EXTENSION = ".png";
        String DELETE_ACCOUNTS_SCHEDULER_START = "DELETE ACCOUNTS SCHEDULER STARTED AT: ";
        String DELETE_ACCOUNTS_SCHEDULER_FINISHED = "DELETE ACCOUNTS SCHEDULER FINISHED AT: ";
    }

    /**
     * Constants for Home Controller
     */
    interface HomeConstants {
        String HTML = "home";
        String URL_HOME = "/home";
    }

    /**
     * Constants for SignIn / SignUp
     */
    interface InitConstants {
        String HTML = "signIn";
        String HTML_ERROR = "signUpError";
        String URL_SIGN_IN = "/signIn";
        String URL_SIGN_UP = "/signUp";
        String URL_SIGN_OUT = "/signOut";
        String URL_ERROR_SIGN_UP = "/errorSignUp";
        String REDIRECT_TO_SIGN_IN = "redirect:/signIn";
        String REDIRECT_TO_SIGN_OUT = "redirect:/signOut";
        String REDIRECT_TO_SIGN_UP_ERROR = "redirect:/errorSignUp";
        String CRUD_SERVICE_QUALIFIER = "userServiceImpl";
    }

    /**
     * Constants for Notes Controller
     */
    interface NotesConstants {
        String MODEL_NOTES = "notes";
        String URL_PAGE = "/notes";
        String URL_DELETE = "/notes/deleteNotes";
        String URL_FIND_BY_ID = "/notes/findById";
        String URL_SAVE = "/notes/add";
        String URL_UPDATE = "/notes/updateNotes";
        String HTML = "notes";
        String REDIRECT_TO_NOTES = "redirect:/notes";
        String CRUD_SERVICE_QUALIFIER = "notesServiceImpl";
    }

    /**
     * Constants for Profile Controller
     */
    interface ProfileConstants {
        String HTML = "profile";
        String URL_PAGE = "/profile";
        String URL_REDIRECT_TO_PAGE = "redirect:/profile";
        String URL_EDIT_USER = "/profile/updateProfile";
        String URL_DELETE_USER = "/profile/deleteAccount";
        String URL_EDIT_PASSWORD = "/profile/updatePassword";
        String CRUD_SERVICE_QUALIFIER = "userServiceImpl";
        String MODEL_USER = "activeUser";
        String MODEL_COUNT_TODOS = "userTodos";
        String MODEL_COUNT_NOTES = "userNotes";
        String PARAM_PASSWORD = "password";
        String PARAM_OLD_PASSWORD = "oldPassword";
        String PARAM_NEW_PASSWORD = "newPassword";
        String PARAM_AVATAR = "avatar";
        String PARAM_FIRST_NAME = "firstName";
        String PARAM_LAST_NAME = "lastName";
        String PARAM_EMAIL = "email";
        String PARAM_PHONE = "phone";
        String PARAM_USERNAME = "username";
    }

    /**
     * Constants for Todo Controller
     */
    interface TodoConstants {
        String MODEL_ACTIVE_TODOS = "activeTodos";
        String MODEL_FINISHED_TODOS = "finishedTodos";
        String URL_PAGE = "/todos";
        String URL_DELETE = "/todos/deleteTodo";
        String URL_FIND_BY_ID = "/todos/findById";
        String URL_SAVE = "/todos/add";
        String URL_UPDATE = "/todos/updateTodo";
        String URL_IS_DONE = "/todos/isDone/";
        String HTML = "todos";
        String REDIRECT_TO_TODOS = "redirect:/todos";
        String CRUD_SERVICE_QUALIFIER = "todoServiceImpl";
    }
}
