import { connect } from "react-redux";
import { StateProps, DispatchProps } from "./props";
import { mapDispatchToProps, mapStateToProps } from "./map-state-to-props";
import { DetailsPage } from "./DetailsPage";

export default connect<StateProps, DispatchProps>(
    mapStateToProps,
    mapDispatchToProps
)(DetailsPage);