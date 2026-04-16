# Complete EMS HR Frontend Build Plan
Status: In Progress 🛠️

## Core Dependencies (Step 1)
```
cd frontend
npm i react-router-dom @tanstack/react-query react-hook-form @tanstack/react-table lucide-react date-fns clsx tailwind-merge axios tailwindcss postcss autoprefixer
```

## Steps:
- [x] 1. Install deps + restart dev server
- [x] 2. Fix api.ts (full axios service with EMS endpoints)
- [x] 3. Update types/index.ts (all DTOs)
- [x] 4. Auth: Login/Signup pages + AuthContext/ProtectedRoute
- [ ] 5. Layout: Sidebar DashboardLayout
- [ ] 6. Pages: Employees(CRUD), Departments(CRUD), Attendance, Salary, Leaves, Advances, Reports
- [ ] 7. Components: DataTable, FormInputs, Modals, Charts
- [ ] 8. Routing + QueryClientProvider
- [ ] 9. Deployable build

**Next: Create Sidebar Layout + Employees page**




