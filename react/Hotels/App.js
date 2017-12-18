import React from 'react';

import {StackNavigator} from 'react-navigation';
import MainWindow from "./src/MainWindow";
import AddHotelWindow from "./src/AddHotelWindow";

global.hotels = [];

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