import React from "react";
import {Button, StyleSheet, Text, View} from "react-native";
import {UserRepository} from "./UserRepository";

export default class HotelsAppWindow extends React.Component {
    constructor() {
        super();
        this.state = {
            email: '',
            password: ''
        };

        this.userRepo = new UserRepository();
        console.log("list from main window" + this.userRepo.users.listOfUsers)
    }

    login() {
        this.props.navigation.navigate("Login", {userRepo: this.userRepo});
    }

    register() {
        this.props.navigation.navigate("Register", {userRepo: this.userRepo});
    }

    render() {
        return (
            <View style={styles.myView}>
                <Text style={styles.myText}>Welcome to Hotels App!!</Text>

                <View style={styles.buttons}>
                    <View style={styles.myButton}>
                        <Button onPress={() => this.login()} title="Login" color='#993366'/>
                    </View>
                    <View style={styles.myButton3}>
                        <Button onPress={() => this.register()} title="Register" color='#993366'/>
                    </View>
                </View>

            </View>
        );
    }
};

const styles = StyleSheet.create({
    myView: {
        height: 600,
        width: 400,
        backgroundColor: '#993366'
    },
    myText: {
        marginTop: 60,
        fontSize: 40,
        textAlign: 'center',
        color: '#ffffcc'
    },
    buttons: {
        flexDirection: 'row',
        marginTop: 200
    },
    myButton: {
        height: 100,
        width: 100,
        marginLeft: 80,
        backgroundColor: '#ffffcc',
        borderRadius: 20,
        padding: 10,
        shadowOffset: {
            width: 0,
            height: 3
        },
        shadowRadius: 10,
        shadowOpacity: 0.25
    },
    myButton3: {
        height: 100,
        width: 105,
        marginLeft: 10,
        backgroundColor: '#ffffcc',
        borderRadius: 20,
        padding: 10,
        shadowOffset: {
            width: 0,
            height: 3
        },
        shadowRadius: 10,
        shadowOpacity: 0.25
    }
});
