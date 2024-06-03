import { connect } from "react-redux";
import { StateProps, DispatchProps } from "./props";
import { mapDispatchToProps, mapStateToProps } from "./map-state-to-props";
import { RegisterPage } from "./RegisterPage";

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(RegisterPage);