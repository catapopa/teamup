import { Technology } from './technology';
import { UserSkillLevel } from './userSkillLevel';

export interface UserSkill {
  id: number;
  technology: Technology;
  level: UserSkillLevel;
}
