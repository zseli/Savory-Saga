import { Component } from '@angular/core';
import { FooterComponent } from '../../layouts/footer/footer.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [FooterComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(private router: Router) { }

  redirectToLogin() {
    this.router.navigate(['/login']);
  }
  
}
