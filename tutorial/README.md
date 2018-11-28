This is the starter kit for Java SDK.

This kit contains a simple web application that displays car information using Smartcar's Java SDK.

## Instructions
Before we get started, create an application on Smartcar's Developer Dashboard to get your API keys.

**Note:** On the dashboard, you will want to set your `redirect_uri` as `http://localhost:8000/callback`.

Then, we can set the clientId and clientSecret within `Main.java` -
```java
AuthClient client = new AuthClient(
  "yourClientId",
  "yourClientSecret",
  "http://localhost:8000/callback",
  true
);
```

To install the required dependencies and run this Java app -
```bash
$ gradle run
```

Once your server is up and running, you can authenticate your vehicle. In our current set up, we are in Smartcar's [test mode](https://smartcar.com), so you can log in with any username and password. To authenticate, navigate to `http://localhost:8000`. Once you have authenticated, go to `http://localhost:8000/vehicle` to see your vehicle information.

## Next Steps
Read our [API Docs](https://smartcar.com/docs) to learn what else you can do with Smartcar's API.
