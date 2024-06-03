import {UserDesign} from "./UserDesign";

export type OwnProps = {
    imgUrl: string;
    text: string;
};

export type StateProps = {
};

export type DispatchProps = {
};

export type UserDesignProps = StateProps & DispatchProps & OwnProps;