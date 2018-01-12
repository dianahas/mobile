import React from 'react';

import {StackNavigator} from 'react-navigation';
import MainWindow from "./src/MainWindow";
import AddHotelWindow from "./src/AddHotelWindow";
import HotelsList from "./src/HotelsList";
import ManageHotelWindow from "./src/ManageHotelWindow";
import HotelsAppWindow from "./src/HotelsAppWindow";
import RegisterWindow from "./src/RegisterWindow";
import LoginWindow from "./src/LoginWindow";

const ModalStack = StackNavigator({
    HotelsApp: {
        screen: HotelsAppWindow
    },
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