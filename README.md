This is the starter kit for Java SDK.

This kit contains a simple web application that displays car information using Smartcar's Java SDK.

## Instructions
Before we get started, create an application on Smartcar's Developer Dashboard to get your API keys.

**Note:** On the dashboard, you will want to set your `redirect_uri` as `http://localhost:8000/exchange`.

Then, we can set these as environment variables -
```bash
$ export CLIENT_ID=<your-client-id>
$ export CLIENT_SECRET=<your-client-secret>
```

Make sure you have cloned this repo -
```bash
$ git clone git@github.com:smartcar/getting-started-java-sdk.git
$ cd getting-started-java-sdk/app
```

To install the required dependencies and run this Java app -
```bash
$ ./gradlew run
```

Once your server is up and running, you can authenticate your vehicle. In our current set up, we are using Smartcar's [test mode](https://smartcar.com), so you can log in with any username and password. To authenticate, navigate to `http://localhost:8000/login`. Once you have authenticated, go to `http://localhost:8000/vehicle` to see your vehicle information.

## Next Steps
Read our [API Docs](https://smartcar.com/docs) to learn what else you can do with Smartcar's API.

Learn more about the [Java SDK](https://github.com/smartcar/java-sdk) and how it can be used.

