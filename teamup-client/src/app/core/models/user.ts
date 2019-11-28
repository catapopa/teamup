import {UserRole} from './userRole';

export interface User {
    id: number;
    username: string;
    email: string;
    firstName: string;
    lastName: string;
    birthDate: Date;
    role: UserRole;
}
