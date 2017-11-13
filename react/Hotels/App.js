import React from 'react';

import {StackNavigator} from 'react-navigation';
import MainWindow from "./src/MainApp";
import AddHotelWindow from "./src/AddHotelWindow";

global.hotels = [
    {
        id: 1,
        name: "hotel",
        location: "location"
    }, {
        id: 2,
        name: "hotel1",
        location: "location2"
    }, {
        id: 3,
        name: "hotel3",
        location: "location3"
    }];
global.count = 4;


const ModalStack = StackNavigator({
    Home: {
        screen: MainWindow,
    },
    AddHotelWindow: {
        screen: AddHotelWindow,
        path: 'AddHotelWindow/:hotel'
    }
});
export default ModalStack;
