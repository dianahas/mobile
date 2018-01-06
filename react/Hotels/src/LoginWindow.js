import React from "react";
import {Button, Text, TextInput, View, StyleSheet} from "react-native";
import {UserRepository} from "./UserRepository";

export default class LoginWindow extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: ''
        };

        this.userRepo = new UserRepository();
        console.log("list from main window" + this.userRepo.users.listOfUsers)
    }

    login() {
        this.props.navigation.navigate("Home");
    }

    register() {
        this.props.navigation.navigate("Register", {userRepo: this.userRepo});
    }

    render() {
        return (
            <View style={styles.myView}>
                <Text style={styles.myText}>Login to App</Text>
                <View style={styles.inputForm}>
                    <TextInput style={styles.inputText1}
                               placeholder="Email..."
                               placeholderTextColor="white"
                               onChangeText={(text) => this.setState({email: text})}
                               value={this.state.email.toString()}/>

                    <TextInput style={styles.inputText2}
                               secureTextEntry={true}
                               placeholder="Password..."
                               placeholderTextColor="white"
                               onChangeText={(text) => this.setState({password: text})}
                               value={this.state.password.toString()}/>
                </View>
                <View style={styles.myButton}>
                    <Button onPress={() => this.login()} title="Login" color='#FDEBD0'/>
                </View>
                <View style={styles.myButton}>
                    <Button onPress={() => this.register()} title="Register" color='#FDEBD0'/>
                </View>
            </View>
        );
    }
};

const styles = StyleSheet.create({
    myView: {
        height: 600,
        width: 400,
        backgroundColor: '#FDEBD0'
    },
    myText: {
        marginTop: 60,
        fontSize: 40,
        textAlign: 'center',
        color: '#48C9B0'
    },
    myButton: {
        height: 100,
        width: 100,
        marginTop: 20,
        marginLeft: 100,
        backgroundColor: '#48C9B0',
        borderRadius: 20,
        padding: 10,
        shadowOffset: {
            width: 0,
            height: 3
        },
        shadowRadius: 10,
        shadowOpacity: 0.25
    },
    inputForm: {
        marginTop: 20
    },
    inputText: {
        height: 30,
        width: 300,
        backgroundColor: '#48C9B0',
        color: 'white',
    },
    inputText1: {
        marginTop: 10,
        height: 30,
        width: 300,
        backgroundColor: '#48C9B0',
        color: 'white',
    },
    inputText2: {
        marginTop: 20,
        height: 30,
        width: 300,
        backgroundColor: '#48C9B0',
        color: 'white',
    }
});
