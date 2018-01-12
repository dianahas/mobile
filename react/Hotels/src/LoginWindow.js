import React from "react";
import {Button, StyleSheet, Text, TextInput, View} from "react-native";

export default class LoginWindow extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            id: 0,
            email: '',
            password: ''
        };

        this.repo = props.navigation.state.params.userRepo;
    }

    login() {
        let logged = this.repo.handleLogin(this.state);
        if (logged)
            this.props.navigation.navigate("Home");
        else this.props.navigation.navigate("HotelsApp");
    }

    render() {
        return (
            <View style={styles.myView}>

                <Text style={styles.myText}>Login to App</Text>

                <View style={styles.inputForm}>
                    <TextInput style={styles.inputText}
                               placeholder="Email..."
                               placeholderTextColor="white"
                               onChangeText={(text) => this.setState({email: text})}
                               value={this.state.email.toString()}/>

                    <TextInput style={styles.inputText1}
                               secureTextEntry={true}
                               placeholder="Password..."
                               placeholderTextColor="white"
                               onChangeText={(text) => this.setState({password: text})}
                               value={this.state.password.toString()}/>
                </View>

                <View style={styles.myButton}>
                    <Button onPress={() => this.login()} title="Login" color='#993366'/>
                </View>

            </View>
        );
    }
}

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
    myButton: {
        height: 60,
        width: 100,
        marginLeft: 20,
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
    inputForm: {
        marginTop: 20
    },
    inputText: {
        marginTop: 10,
        marginLeft: 20,
        height: 30,
        width: 300,
        backgroundColor: '#993366',
        color: '#ffffcc',
    },
    inputText1: {
        marginTop: 10,
        marginLeft: 20,
        height: 30,
        width: 300,
        backgroundColor: '#993366',
        color: '#ffffcc',
    },
});
