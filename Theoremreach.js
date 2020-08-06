import { NativeModules, NativeEventEmitter } from 'react-native';

const RNTheoremreach = NativeModules.RNTheoremreach;
const TheoremreachEventEmitter = new NativeEventEmitter(RNTheoremreach);

const eventHandlers = {
  surveyOpened: new Map(),
  surveyClosed: new Map()
};

const addEventListener = (type, handler) => {
  switch (type) {
    case 'surveyOpened':
    case 'surveyClosed':
      eventHandlers[type].set(handler, TheoremreachEventEmitter.addListener(type, handler));
      break;
    default:
      console.log(`Event with type ${type} does not exist.`);
  }
}

const removeEventListener = (type, handler) => {
  if (!eventHandlers[type].has(handler)) {
    return;
  }
  eventHandlers[type].get(handler).remove();
  eventHandlers[type].delete(handler);
}

const removeAllListeners = () => {
  TheoremreachEventEmitter.removeAllListeners('surveyOpened');
  TheoremreachEventEmitter.removeAllListeners('surveyClosed');
};

module.exports = {
  ...RNPollfish,
  initialize: (key,  userId) => RNPollfish.initialize(key, userId),
  show: () => RNPollfish.show(),
  surveyAvailable: () => RNPollfish.surveyAvailable(),
  addEventListener,
  removeEventListener,
  removeAllListeners
};
