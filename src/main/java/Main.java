import static spark.Spark.*;
import com.smartcar.sdk.*;
import com.smartcar.sdk.data.*;

public class Main {
  // global variable to save our accessToken
  private static String access;

  public static void main(String[] args) {

    port(8007);

    AuthClient client = new AuthClient(
		"ea1a2282-4dc3-4def-81d7-cee691731455",
		"045d4bd0-f727-47d9-a76a-77362a28d8fa",
		"http://localhost:8080/callback",
		true
    );

    get("/login", (req, res) -> {
      String link = client.getAuthUrl();
      res.redirect(link);
      return null;
    });

    get("/callback", (req, res) -> {
      String code = req.queryMap("code").value();
      Auth auth = client.exchangeCode(code);

      // in a production app you'll want to store this in some kind of persistent storage
      access = auth.getAccessToken();

      res.redirect("/vehicle");
      return null;
    });

    get("/vehicle", (req, res) -> {
      SmartcarResponse<VehicleIds> vehicleIdResponse = AuthClient.getVehicleIds(access);
      String[] vehicleIds = vehicleIdResponse.getData().getVehicleIds();

      // instantiate the first vehicle in the vehicle id list
      Vehicle vehicle = new Vehicle(vehicleIds[0], access);

      VehicleInfo info = vehicle.info();

      // {
      //   "id": "36ab27d0-fd9d-4455-823a-ce30af709ffc",
      //   "make": "TESLA",
      //   "model": "Model S",
      //   "year": 2014
      // }

      System.out.println(info.toString());

      return info.toString();
    });
  }
}
