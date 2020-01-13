import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { UsersComponent } from './components/users/users.component';
import { ApproveProfileComponent } from './components/approve-profile/approve-profile.component';
import { InviteComponent } from './components/invite/invite.component';


const homeRoutes: Routes = [
  {
    path: 'profile', component: ProfileComponent
  },
  {
    path: 'users', component: UsersComponent
  },
  {
    path: 'invite', component: InviteComponent
  },
  {
    path: '',
    redirectTo: 'profile',
    pathMatch: 'full',
  },
  {
    path: 'profiles/:username', component: ApproveProfileComponent
  }
];

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'home',
    children: homeRoutes
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule
  ]
})
export class AppRoutingModule {
}
