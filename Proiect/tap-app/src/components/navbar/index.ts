import {connect} from "react-redux";
import {mapDispatchToProps, mapStateToProps} from "./map-state-to-props";
import {Navbar} from "./Navbar";

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Navbar);