import React from 'react';

import {StackNavigator} from 'react-navigation';
import MainWindow from "./src/MainWindow";
import AddHotelWindow from "./src/AddHotelWindow";
import HotelsList from "./src/HotelsList";
import ManageHotelWindow from "./src/ManageHotelWindow";
import LoginWindow from "./src/LoginWindow";
import RegisterWindow from "./src/RegisterWindow";

const ModalStack = StackNavigator({
    Login: {
        screen: LoginWindow
    },
    Register: {
        screen: RegisterWindow
    },
    Home: {
        screen: MainWindow,
    },
    AddHotelWindow: {
        screen: AddHotelWindow,
        path: 'AddHotelWindow/:hotel'
    },
    HotelsList: {screen: HotelsList},
    ManageHotelWindow: {screen: ManageHotelWindow, path: 'ManageHotelWindow/:selected'}
});
export default ModalStack;