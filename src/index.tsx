import { NativeModules } from 'react-native';

type TheoremreachType = {
  multiply(a: number, b: number): Promise<number>;
};

const { Theoremreach } = NativeModules;

export default Theoremreach as TheoremreachType;
