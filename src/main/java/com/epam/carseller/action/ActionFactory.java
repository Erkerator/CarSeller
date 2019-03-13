package com.epam.carseller.action;

import java.util.HashMap;
import java.util.Map;

import static com.epam.carseller.util.CarsellerConstants.*;

public class ActionFactory {

    private static final Map<String, Action> ACTION_MAP = new HashMap<>();
    private static final ActionFactory ACTION_FACTORY = new ActionFactory();

    private ActionFactory() {
        init();
    }

    private void init() {
        ACTION_MAP.put(USER_REGISTRATION, new UserRegistrationAction());
        ACTION_MAP.put(GET_LIST_OF_CARS, new GetListOfCarsAction());
        ACTION_MAP.put(WELCOME_PAGE, new WelcomePageAction());
        ACTION_MAP.put(LOGIN_PAGE, new LoginPageAction());
        ACTION_MAP.put(REGISTRATION_PAGE, new RegistrationPageAction());
        ACTION_MAP.put(LOGIN_USER, new LoginUserAction());
        ACTION_MAP.put(ACCOUNT_PAGE, new AccountPageAction());
        ACTION_MAP.put(LOGOUT, new LogoutAction());
        ACTION_MAP.put(CAR_DETAILS, new ShowCarDetailsAction());
        ACTION_MAP.put(DELETE_CAR, new DeleteCarAction());
        ACTION_MAP.put(ADD_CAR, new AddCarPageAction());
        ACTION_MAP.put(SAVE_CAR_TO_DB, new SaveCarAction());
        ACTION_MAP.put(ADMIN_PAGE, new AdministrateAction());
        ACTION_MAP.put(CATEGORIES_ADMIN_PAGE, new ShowCategoriesAction());
        ACTION_MAP.put(ADD_CATEGORY, new AddCategoryAction());
    }

    public static ActionFactory getInstance() {
        return ACTION_FACTORY;
    }

    public Action getAction(String actionRequest) {
        return ACTION_MAP.get(actionRequest);
    }
}
