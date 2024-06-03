import { connect } from "react-redux";
import { mapDispatchToProps, mapStateToProps } from "./map-state-to-props";
import { LoginPage } from "./LoginPage";

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(LoginPage);