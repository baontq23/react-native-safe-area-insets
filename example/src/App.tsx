import { Text, View, StyleSheet } from 'react-native';
import SafeAreaInsets from 'react-native-safe-area-insets';

export default function App() {
  return (
    <View style={styles.container}>
      <Text>Top: {SafeAreaInsets.getSafeAreaInsets().top}</Text>
      <Text>Bottom: {SafeAreaInsets.getSafeAreaInsets().bottom}</Text>
      <Text>Left: {SafeAreaInsets.getSafeAreaInsets().left}</Text>
      <Text>Right: {SafeAreaInsets.getSafeAreaInsets().right}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
