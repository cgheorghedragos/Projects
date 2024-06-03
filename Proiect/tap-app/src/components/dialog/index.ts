import {connect} from "react-redux";
import {mapStateToProps} from "./map-state-to-props";
import Dialog from "./Dialog";

export default connect(
    mapStateToProps
)(Dialog);