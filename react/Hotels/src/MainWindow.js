import {HotelsRepository} from "./HotelsRepository";
import React from 'react';
import {Button, StyleSheet, Text, View} from "react-native";

export default class Main extends React.Component {
    constructor() {
        super();
        this._repo = new HotelsRepository();
        console.log("list from main window" + this._repo.data.listOfHotels)
    }

    onPress1() {
        this.props.navigation.navigate("HotelsList", {repo: this._repo});
    }

    onPress2() {
        this.props.navigation.navigate("AddHotelWindow", {repo: this._repo});
    }

    render() {
        return ( //we cand return just one element
            <View style={styles.myView}>
                <Text style={styles.myText}>HotelsApp</Text>

                <View style={styles.buttons}>
                    <View style={styles.myButton}>
                        <Button onPress={() => this.onPress1()} title="View List" color='#993366'/>
                    </View>

                    <View style={styles.myButton3}>
                        <Button onPress={() => this.onPress2()} title="Add" color='#993366'/>
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
        borderRadius: 10,
        padding: 10,
        shadowOffset: {
            width: 0,
            height: 3
        },
        shadowRadius: 10,
        shadowOpacity: 0.25
    }
});