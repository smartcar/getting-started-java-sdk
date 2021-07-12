import static spark.Spark.*;
import com.google.gson.Gson;
import com.smartcar.sdk.*;
import com.smartcar.sdk.data.*;

public class Main {
  // global variable to save our accessToken
  private static String access;
  private static Gson gson = new Gson();

  public static void main(String[] args) throws Exception {

    port(8000);

    String[] scope = {"required:read_vehicle_info", "required:read_odometer"};
    boolean testMode = true;

    AuthClient client = new AuthClient.Builder()
      .clientId("2cfa25d8-f912-4b1f-905a-4562336f2940")
      .clientSecret("fc78622f-9b71-4acf-9713-cc6e70945e7f")
      .redirectUri("http://localhost:8000/exchange")
      .testMode(testMode)
      .build();

    get("/login", (req, res) -> {
      String link = client.authUrlBuilder(scope).build();
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
      String[] vehicleIds = vehiclesResponse.getVehicleIds();

      // instantiate the first vehicle in the vehicle id list
      Vehicle vehicle = new Vehicle(vehicleIds[0], access);
  
      VehicleAttributes attributes = vehicle.attributes();
      System.out.println(gson.toJson(attributes));

      // {
      //   "id": "36ab27d0-fd9d-4455-823a-ce30af709ffc",
      //   "make": "TESLA",
      //   "model": "Model S",
      //   "year": 2014
      // }

      res.type("application/json");
      return gson.toJson(attributes);
    });
  }
}
