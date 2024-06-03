import { connect } from "react-redux";
import { mapDispatchToProps, mapStateToProps } from "./map-state-to-props";
import { Section } from "./Section";

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Section);