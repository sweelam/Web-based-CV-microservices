import { TestBed, inject } from '@angular/core/testing';

import { AppInterceptorService } from './app-interceptor.service';

describe('AppInterceptorService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AppInterceptorService]
    });
  });

  it('should be created', inject([AppInterceptorService], (service: AppInterceptorService) => {
    expect(service).toBeTruthy();
  }));
});
