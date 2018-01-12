import React from "react";
import {Button, Text, TextInput, View, StyleSheet} from "react-native";

export default class RegisterWindow extends React.Component {
    constructor(props) {
        super(props);
        this.userRepo = props.navigation.state.params.userRepo;

        this.state = {
            email: '',
            password: ''
        };

    }

    register() {
        this.userRepo.handleAddUser(this.state);
        console.log("After user add" + JSON.stringify(this.userRepo.users));

        this.props.navigation.goBack();
    }

    render() {
        return (
            <View style={styles.myView}>
                <Text style={styles.myText}>Register</Text>
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
                    <Button onPress={() => this.register()} title="Register" color='#993366'/>
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