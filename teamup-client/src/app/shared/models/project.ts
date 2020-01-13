import {Industry} from './industry';
import {Company} from './company';
import {UserExperience} from './userExperience';

export interface Project {
    id: number;
    name: string;
    description: string;
    industry: Industry;
    company: Company;
    userExperiences: UserExperience[];
}
