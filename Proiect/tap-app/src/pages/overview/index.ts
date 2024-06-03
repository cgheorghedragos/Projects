import { connect } from "react-redux";
import { OverviewPage } from "./OverviewPage";
import {mapDispatchToProps, mapStateToProps} from "./map-state-to-props";

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(OverviewPage);