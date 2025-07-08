package endpoints;

public class Routes {

    // Base URL
    public static String base_url = "https://petstore.swagger.io/v2";

    // ----------------------------------
    // USER MODULE
    // ----------------------------------
    public static String post_user = base_url + "/user";
    public static String get_user = base_url + "/user/{username}";
    public static String update_user = base_url + "/user/{username}";
    public static String delete_user = base_url + "/user/{username}";
    public static String login_user = base_url + "/user/login";
    public static String logout_user = base_url + "/user/logout";
    public static String create_user_with_array = base_url + "/user/createWithArray";
    public static String create_user_with_list = base_url + "/user/createWithList";

    // ----------------------------------
    // PET MODULE
    // ----------------------------------
    public static String post_pet = base_url + "/pet";
    public static String get_pet_by_id = base_url + "/pet/{petId}";
    public static String update_pet = base_url + "/pet";
    public static String update_pet_with_form = base_url + "/pet/{petId}";
    public static String delete_pet = base_url + "/pet/{petId}";
    public static String find_pets_by_status = base_url + "/pet/findByStatus";
    public static String find_pets_by_tags = base_url + "/pet/findByTags";
    public static String upload_pet_image = base_url + "/pet/{petId}/uploadImage";

    // ----------------------------------
    // STORE MODULE
    // ----------------------------------
    public static String place_order = base_url + "/store/order";
    public static String get_order_by_id = base_url + "/store/order/{orderId}";
    public static String delete_order = base_url + "/store/order/{orderId}";
    public static String get_inventory = base_url + "/store/inventory";
}
