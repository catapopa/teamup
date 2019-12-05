import { UserRole } from './userRole';
import { UserLanguage } from 'src/app/shared/models/userLanguage';
import { UserSeniority } from 'src/app/shared/models/userSeniority';
import { Company } from 'src/app/shared/models/company';
import { UserSkill } from 'src/app/shared/models/userSkill';
import { ProjectExperience } from 'src/app/shared/models/projectExperience';

export interface User {
    id: number;
    username: string;
    password: string;
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

