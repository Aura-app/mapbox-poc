/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import type { PropsWithChildren } from 'react';
import {
    Button,
    SafeAreaView,
    ScrollView,
    StatusBar,
    StyleSheet,
    Text,
    useColorScheme,
    View,
} from 'react-native';

import {
    Colors,
    DebugInstructions,
    Header,
    ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';
import { NativeModules } from 'react-native';
import { PermissionsAndroid } from 'react-native';

type SectionProps = PropsWithChildren<{
    title: string;
}>;

function Section({ children, title }: SectionProps): JSX.Element {
    const isDarkMode = useColorScheme() === 'dark';
    return (
        <View style={styles.sectionContainer}>
            <Text
                style={[
                    styles.sectionTitle,
                    {
                        color: isDarkMode ? Colors.white : Colors.black,
                    },
                ]}>
                {title}
            </Text>
            <Text
                style={[
                    styles.sectionDescription,
                    {
                        color: isDarkMode ? Colors.light : Colors.dark,
                    },
                ]}>
                {children}
            </Text>
        </View>
    );
}

function App(): JSX.Element {
    const isDarkMode = useColorScheme() === 'dark';

    const backgroundStyle = {
        backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
    };

    const { MapboxNavigationModule, TestModule } = NativeModules;

    const openNavigationView = async () => {
        try {
            MapboxNavigationModule.navigateToNavigationView()
        } catch (err) {
            console.warn(err);
        }
    };

    const openTurnByTurn= async () => {
        try {
            MapboxNavigationModule.navigateToTurnByTurn()
        } catch (err) {
            console.warn(err);
        }
    };

    const requestLocationPermission = async () => {
        try {
            const granted = await PermissionsAndroid.request(
                PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION,
                {
                    title: 'Need to know where you are Permission',
                    message:
                        'Gief location access ' +
                        'so that I can navigate you.',
                    buttonNeutral: 'Ask Me Later',
                    buttonNegative: 'Cancel',
                    buttonPositive: 'OK',
                },
            );
            console.log(granted)
            if (granted === PermissionsAndroid.RESULTS.GRANTED) {
                console.log('You can use location');
            } else {
                console.log('Location permission denied');
            }
        } catch (err) {
            console.warn(err);
        }
    };

    const requestNotificationPermission = async () => {
        try {
            const granted = await PermissionsAndroid.request(
                PermissionsAndroid.PERMISSIONS.POST_NOTIFICATIONS,
                {
                    title: 'Be notifed Dammit Permission',
                    message:
                        'Gief permissions to notify ' +
                        'so that I can notify you.',
                    buttonNeutral: 'Ask Me Later',
                    buttonNegative: 'Cancel',
                    buttonPositive: 'OK',
                },
            );
              console.log(granted)
            if (granted === PermissionsAndroid.RESULTS.GRANTED) {
                console.log('You can use notfications');
            } else {
                console.log('Notifications permission denied');
            }
        } catch (err) {
            console.warn(err);
        }
    };

    return (
        // <View>
        //     {MapboxNavigationModule.navigateToNative()}
        // </View>
        <SafeAreaView style={backgroundStyle}>
            <StatusBar
                barStyle={isDarkMode ? 'light-content' : 'dark-content'}
                backgroundColor={backgroundStyle.backgroundColor}
            />
            <ScrollView
                contentInsetAdjustmentBehavior="automatic"
                style={backgroundStyle}>
                <Header />
                <View
                    style={{
                        backgroundColor: isDarkMode ? Colors.black : Colors.white,
                    }}>

                    {/* <TestModuleButton /> */}
                    <Button title="Open Navigation View" onPress={openNavigationView} />
                    <Button title="Open Turn By Turn View" onPress={openTurnByTurn} />
                    <Button title="request location permissions" onPress={requestLocationPermission} />
                    <Button title="request notification permissions" onPress={requestNotificationPermission} />
                </View>
            </ScrollView>
        </SafeAreaView>
    );
}

const styles = StyleSheet.create({
    sectionContainer: {
        marginTop: 32,
        paddingHorizontal: 24,
    },
    sectionTitle: {
        fontSize: 24,
        fontWeight: '600',
    },
    sectionDescription: {
        marginTop: 8,
        fontSize: 18,
        fontWeight: '400',
    },
    highlight: {
        fontWeight: '700',
    },
});

export default App;
