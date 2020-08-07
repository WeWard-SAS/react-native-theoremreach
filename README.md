# react-native-theoremreach

native module for theoremreach

## Installation

```sh
yarn add react-native-theoremreach
```

#### iOS
```sh
pod install
```

## Usage

### Initialize TheoremReach
First, you need to initialize the TheoremReach instance with `initWithApiKeyAndUserId` call.
```javascript
// Import TheoremReach native module
import RNTheoremReach from 'react-native-theorem-reach';
componentWillMount() {
  // In your app initialization, initialize TheoremReach
  RNTheoremReach.initWithApiKeyAndUserId('YOUR_API_TOKEN', 'YOUR_USER_ID');
}
```

### Reward Center
Next, implement the logic to display the reward center. Call the `showRewardCenter` method when you are ready to send the user into the reward center where they can complete surveys in exchange for your virtual currency. We automatically convert the amount of currency a user gets based on the conversion rate specified in your app.

```javascript
onPressShowRewardCenter = () => {
  RNTheoremReach.isSurveyAvailable((isAvailable) => {
    // if a survey is available, show the reward center
    if (isAvailable) {
      RNTheoremReach.showRewardCenter();
    }
  })
}
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

