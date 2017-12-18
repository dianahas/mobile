import React from "react";
import {Button, Picker, StyleSheet, Text, TextInput, View} from "react-native";

export default class ManageHotelWindow extends React.Component {
    constructor(props) {
        super(props);
        this.repo = props.navigation.state.params.repo;

        this.state = {
            id: 0,
            name: "",
            location: ""
        };

        let currentHotel = this.props.navigation.state.params.selected.hotel;
        this.state.id = currentHotel.id;
        this.state.name = currentHotel.name;
        this.state.location = currentHotel.location;
    }


    onPress1() {
        this.repo.handleChangedObject(this.state);
        this.props.navigation.navigate("HotelsList", {repo: this.repo});
    }

    onPress2() {
        this.repo.handleClickedDelete(this.state);
        this.props.navigation.navigate("HotelsList", {repo: this.repo});
    }

    render() {
        return (
            <View style={styles.myView}>
                <Text style={styles.myText}>Manage hotel</Text>
                <View style={styles.inputForm}>

                    <TextInput style={styles.inputText}
                               onChangeText={(text) => this.setState({name: text})}
                               value={this.state.name}/>

                    <Picker
                        selectedValue={this.state.location}
                        onValueChange={(itemValue, itemIndex) => this.setState({location: itemValue})}>
                        <Picker.Item label="Italy" value="Italy" />
                        <Picker.Item label="Germany" value="Germany" />
                        <Picker.Item label="USA" value="USA" />
                        <Picker.Item label="Romania" value="Romania" />
                    </Picker>

                </View>
                <View style={styles.buttons}>
                    <View style={styles.myButton}>
                        <Button onPress={() => this.onPress1()} title="Submit" color='#993366'/>
                    </View>
                    <View style={styles.myButton}>
                        <Button onPress={() => this.onPress2()} title="Delete" color='#993366'/>
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
        fontSize: 60,
        textAlign: 'center',
        color: '#ffffcc'
    },
    inputText: {
        marginTop: 10,
        marginLeft: 20,
        height: 30,
        width: 300,
        backgroundColor: '#993366',
        color: '#ffffcc',
    },
    buttons: {
        flexDirection: 'row',
        marginTop: 200
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
    inputText1: {
        marginTop: 10,
        marginLeft: 20,
        height: 30,
        width: 300,
        backgroundColor: '#993366',
        color: '#ffffcc',
    },
    myList: {
        marginTop: 50
    },
    listItem: {
        marginTop: 10,
        fontSize: 20,
        color: '#48C9B0'
    }
});