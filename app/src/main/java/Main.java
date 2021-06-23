import static spark.Spark.*;
import com.google.gson.Gson;
import com.smartcar.sdk.*;
import com.smartcar.sdk.data.*;

public class Main {
  // global variable to save our accessToken
  private static String access;
  private static Gson gson = new Gson();

  public static void main(String[] args) {

    port(8000);

    String[] scope = {"required:read_vehicle_info"};
    boolean testMode = true;

    AuthClient client = new AuthClient.Builder().testMode(testMode).build();

    get("/login", (req, res) -> {
      String link = client.getAuthUrl(scope);
      res.redirect(link);
      return null;
    });

    get("/exchange", (req, res) -> {
      String code = req.queryMap("code").value();

      Auth auth = client.exchangeCode(code);

      // in a production app you'll want to store this in some kind of persistent storage
      access = auth.getAccessToken();

      return "";
    });

    get("/vehicle", (req, res) -> {
      VehicleIds vehiclesResponse = Smartcar.getVehicles(access);
      // the list of vehicle ids
      String[] vehicleIds = vehicleIdResponse.getVehicleIds();

      // instantiate the first vehicle in the vehicle id list
      Vehicle vehicle = new Vehicle(vehicleIds[0], access);

      VehicleInfo info = vehicle.info();

      System.out.println(gson.toJson(info));

      // {
      //   "id": "36ab27d0-fd9d-4455-823a-ce30af709ffc",
      //   "make": "TESLA",
      //   "model": "Model S",
      //   "year": 2014
      // }

      res.type("application/json");

      return gson.toJson(info);
    });
  }
}
