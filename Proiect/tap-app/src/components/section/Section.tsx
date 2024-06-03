import React, {FunctionComponent, useState} from 'react'
import {SectionProps} from './props'
import styles from "./Section.module.css";
import ProjectSectionSummary from "../projectSummary";
import {Experience} from "../experience/Experience";
import DialogForm from "../dialog/index";

export const Section: FunctionComponent<SectionProps> = ({
                                                             sectionTitle,
                                                             dispatchSaveExperience,
                                                             dispatchUpdateExperience,
                                                             dispatchDeleteExperience,
                                                             dispatchDeleteStudies,
                                                             dispatchSaveStudies,
                                                             dispatchUpdateStudies,
                                                             studies,
                                                             experience,
                                                             projects,
                                                             dispatchSaveProject,
                                                             isPersonalAccount
                                                         }) => {
    const [showAddNewStudy, setShowAddNewStudy] = useState(false);
    const [showAddNewExperience, setShowAddNewExperience] = useState(false);
    const [showAddNewProject, setShowAddNewProject] = useState(false);

    return (
        <div className={styles.mainClass}>
            <div className={styles.container}>

                <div className={styles.header}>
                    <h1>Studies: </h1>
                    {isPersonalAccount &&
                        <button className={styles.button} onClick={() => setShowAddNewStudy(true)}>+</button>}
                </div>
                {studies?.map((study) => (
                    <Experience
                        id={study.id || 0}
                        title={study.location}
                        isPersonalAccount={isPersonalAccount}
                        startDate={study.startDate ? study.startDate : new Date()}
                        endDate={study.endDate ? study.endDate : new Date()}
                        description={study.description}
                        onUpdateDispatch={dispatchUpdateStudies}
                        onDeleteDispatch={dispatchDeleteStudies}

                    />
                ))}
                {(!studies || !studies[0]) && (<p className={styles["no-data"]}>No Data</p>)}

                <div className={styles.header}>
                    <h1>Experience: </h1>
                    {isPersonalAccount && (
                        <button className={styles.button} onClick={() => setShowAddNewExperience(true)}>+</button>)}
                </div>
                {(!experience || !experience[0]) && (<p className={styles["no-data"]}>No Data</p>)}

                {experience?.map((experience) => (
                    <Experience
                        id={experience.id || 0}
                        title={experience.location}
                        isPersonalAccount={isPersonalAccount}
                        startDate={experience.startDate ? experience.startDate : new Date()}
                        endDate={experience.endDate ? experience.endDate : new Date()}
                        description={experience.description}
                        onUpdateDispatch={dispatchUpdateExperience}
                        onDeleteDispatch={dispatchDeleteExperience}/>
                ))}

                <div className={styles.header}>
                    <h1>Projects: </h1>
                    {isPersonalAccount && (
                        <button className={styles.button} onClick={() => setShowAddNewProject(true)}>+</button>)}
                </div>
                {(!projects || !projects[0]) && (<p className={styles["no-data"]}>No Data</p>)}

            </div>


            <div style={{display: "flex", flexDirection: "row", alignItems: "center"}}>
                {projects?.map((project) => (

                    <ProjectSectionSummary
                        imgUrl={(project.photos && project.photos.length > 0 && project.photos[0].photoUrl) ? project.photos[0].photoUrl :
                            "https://via.placeholder.com/800x1200"}
                        text={project.description ? project.description : "No description"}
                    />
                ))}
            </div>


            {showAddNewStudy && isPersonalAccount && (
                <DialogForm onClose={() => {
                    setShowAddNewStudy(false)
                }}
                            onDispatch={dispatchSaveStudies}
                            isPersonalProject={false}
                            title={"Add new study"}/>
            )}
            {showAddNewExperience && isPersonalAccount && (
                <DialogForm onClose={() => {
                    setShowAddNewExperience(false)
                }}
                            onDispatch={dispatchSaveExperience}
                            isPersonalProject={false}
                            title={"Add new Experience"}/>
            )}
            {showAddNewProject && isPersonalAccount && (
                <DialogForm onClose={() => {
                    setShowAddNewProject(false)
                }}
                            onDispatch={dispatchSaveProject}
                            isPersonalProject={true}
                            title={"Add new Project"}/>
            )}
        </div>
    )
}