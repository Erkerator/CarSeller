package com.company.carseller.action;

import com.company.carseller.util.CarsellerConstants;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    private static final Map<String, Action> ACTION_MAP = new HashMap<>();
    private static final ActionFactory ACTION_FACTORY = new ActionFactory();

    private ActionFactory() {
        init();
    }

    private void init() {
        ACTION_MAP.put(CarsellerConstants.USER_REGISTRATION, new UserRegistrationAction());
        ACTION_MAP.put(CarsellerConstants.GET_LIST_OF_CARS, new GetListOfCarsAction());
        ACTION_MAP.put(CarsellerConstants.WELCOME_PAGE, new WelcomePageAction());
        ACTION_MAP.put(CarsellerConstants.LOGIN_PAGE, new LoginPageAction());
        ACTION_MAP.put(CarsellerConstants.REGISTRATION_PAGE, new RegistrationPageAction());
        ACTION_MAP.put(CarsellerConstants.LOGIN_USER, new LoginUserAction());
        ACTION_MAP.put(CarsellerConstants.ACCOUNT_PAGE, new AccountPageAction());
        ACTION_MAP.put(CarsellerConstants.LOGOUT, new LogoutAction());
        ACTION_MAP.put(CarsellerConstants.CAR_DETAILS, new ShowCarDetailsAction());
        ACTION_MAP.put(CarsellerConstants.DELETE_CAR, new DeleteCarAction());
        ACTION_MAP.put(CarsellerConstants.ADD_CAR, new AddCarPageAction());
        ACTION_MAP.put(CarsellerConstants.SAVE_CAR_TO_DB, new SaveCarAction());
        ACTION_MAP.put(CarsellerConstants.ADMIN_PAGE, new AdministrateAction());
        ACTION_MAP.put(CarsellerConstants.CATEGORIES_ADMIN_PAGE, new ShowCategoriesAction());
        ACTION_MAP.put(CarsellerConstants.MODELS_ADMIN_PAGE, new ShowModelsAction());
        ACTION_MAP.put(CarsellerConstants.TRANSMISSIONS_ADMIN_PAGE, new ShowTransmissionsAction());
        ACTION_MAP.put(CarsellerConstants.STATE_ADMIN_PAGE, new ShowStatesAction());
        ACTION_MAP.put(CarsellerConstants.BRAND_ADMIN_PAGE, new ShowBrandsAction());
        ACTION_MAP.put(CarsellerConstants.ADD_BRAND, new AddBrandAction());
        ACTION_MAP.put(CarsellerConstants.CORRECT_BRAND, new CorrectBrandAction());
        ACTION_MAP.put(CarsellerConstants.DELETE_BRAND, new DeleteBrandAction());
        ACTION_MAP.put(CarsellerConstants.ADD_CATEGORY, new AddCategoryAction());
        ACTION_MAP.put(CarsellerConstants.CORRECT_CATEGORY, new CorrectCategoryAction());
        ACTION_MAP.put(CarsellerConstants.DELETE_CATEGORY, new DeleteCategoryAction());
        ACTION_MAP.put(CarsellerConstants.ADD_MODEL, new AddModelAction());
        ACTION_MAP.put(CarsellerConstants.CORRECT_MODEL, new CorrectModelAction());
        ACTION_MAP.put(CarsellerConstants.DELETE_MODEL, new DeleteModelAction());
        ACTION_MAP.put(CarsellerConstants.ADD_TRANSMISSION, new AddTransmissionAction());
        ACTION_MAP.put(CarsellerConstants.CORRECT_TRANSMISSION, new CorrectTransmissionAction());
        ACTION_MAP.put(CarsellerConstants.DELETE_TRANSMISSION, new DeleteTransmissionAction());
        ACTION_MAP.put(CarsellerConstants.ADD_STATE, new AddStateAction());
        ACTION_MAP.put(CarsellerConstants.CORRECT_STATE, new CorrectStateAction());
        ACTION_MAP.put(CarsellerConstants.DELETE_STATE, new DeleteStateAction());
        ACTION_MAP.put(CarsellerConstants.ADD_ADMIN_PAGE, new AdminRegistrationPageAction());
        ACTION_MAP.put(CarsellerConstants.ADMIN_REGISTRATION, new AdminRegistrationAction());
        ACTION_MAP.put(CarsellerConstants.CHECK_USERNAME, new CheckUsernameAction());
        ACTION_MAP.put(CarsellerConstants.GET_MODELS, new GetModelsAction());
    }

    public static ActionFactory getInstance() {
        return ACTION_FACTORY;
    }

    public Action getAction(String actionRequest) {
        return ACTION_MAP.get(actionRequest);
    }
}
