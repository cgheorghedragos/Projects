import { connect } from "react-redux";
import { mapDispatchToProps, mapStateToProps } from "./map-state-to-props";
import { SearchPage } from "./SearchPage";

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(SearchPage);