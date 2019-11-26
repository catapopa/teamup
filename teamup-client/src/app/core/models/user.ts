import {UserRole} from './userRole';
import {UserLanguage} from "./userLanguage";
import {UserSeniority} from "./userSeniority";
import {Company} from "./company";
import {UserSkill} from "./userSkill";
import {ProjectExperience} from "./projectExperience";

export interface User {
    id: number;
    username: string;
    password: string
    email: string;
    firstName: string;
    lastName: string;
    birthDate: Date;
    picture: string;
    language: UserLanguage;
    role: UserRole;
    seniority: UserSeniority;
    location: Location;
    company: Company;
    skills: UserSkill[];
    projectExperiences: ProjectExperience[]
}

