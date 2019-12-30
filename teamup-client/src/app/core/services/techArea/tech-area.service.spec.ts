import { TestBed } from '@angular/core/testing';

import { TechAreaService } from './tech-area.service';

describe('TechAreaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TechAreaService = TestBed.get(TechAreaService);
    expect(service).toBeTruthy();
  });
});
