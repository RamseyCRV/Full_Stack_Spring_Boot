package com.libra.Config;

/**
 * Custom constants
 */
public interface LibraConstants {

    /**
     * Controllers constants
     */
    interface Controllers{

        /**
         * Constants for SignIn / SignUp
         */
        interface Init {
            String VIEW = "signIn";
            String VIEW_ERROR = "signUpError";
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
        interface Notes {
            String VIEW = "notes";
            String MODEL_NOTES = "notes";
            String URL_PAGE = "/notes";
            String URL_DELETE = "/deleteNotes";
            String URL_FIND_BY_ID = "/findById";
            String URL_ADD = "/add";
            String URL_UPDATE = "/updateNotes";
            String URL_REDIRECT = "redirect:/notes";
            String CRUD_SERVICE_QUALIFIER = "notesServiceImpl";
        }

        /**
         * Constants for Todo Controller
         */
        interface Todo {
            String MODEL_ACTIVE_TODOS = "activeTodos";
            String MODEL_FINISHED_TODOS = "finishedTodos";
            String URL_PAGE = "/todos";
            String URL_DELETE = "/deleteTodo";
            String URL_FIND_BY_ID = "/findById";
            String URL_SAVE = "/add";
            String URL_UPDATE = "/updateTodo";
            String URL_IS_DONE = "/isDone/";
            String VIEW = "todos";
            String REDIRECT_TO_TODOS = "redirect:/todos";
            String CRUD_SERVICE_QUALIFIER = "todoServiceImpl";
        }

        /**
         * Constants for News Controller
         */
        interface News {
            String VIEW = "home";
            String URL_PAGE = "/news";
            String URL_REDIRECT = "redirect:/";
            String URL_SEARCH = "/news_search";
            String NEWS_DEFULT = "everything";
            String NEWS_SORT = "recent";
            String NEWS_MODEL = "newsApi";
        }

        /**
         * Constants for Profile Controller
         */
        interface Profile {
            String VIEW = "profile";
            String URL_PAGE = "/profile";
            String URL_REDIRECT_TO_PAGE = "redirect:/profile";
            String URL_EDIT_USER = "/updateProfile";
            String URL_DELETE_USER = "/deleteAccount";
            String URL_EDIT_PASSWORD = "/updatePassword";
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
         * Coronavirus controller constants
         */
        interface Coronavirus {
            String URL_PAGE = "/coronavirus";
            String VIEW = "coronavirus";
        }

    }

    /**
     * Configuration constants
     */
    interface ConfigConstants {
        String AVATAR_USER_PATH = "src/main/resources/static/images/avatars/";
        String PNG_EXTENSION = ".png";
        String DELETE_ACCOUNTS_SCHEDULER_START = "DELETE ACCOUNTS SCHEDULER STARTED AT: ";
        String DELETE_ACCOUNTS_SCHEDULER_FINISHED = "DELETE ACCOUNTS SCHEDULER FINISHED AT: ";
    }

}
