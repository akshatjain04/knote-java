// ********RoostGPT********
// Generated Playwright test using RoostGPT

// ********RoostGPT********
const { test, expect } = require('@playwright/test');

test.describe('Roost.ai UI Automation', () => {
  let context;

  test.beforeAll(async ({ browser }) => {
    context = await browser.newContext({
      baseURL: 'https://app.roost.ai',
      // Set the theme cookie to 'dark'
      storageState: {
        cookies: [
          {
            name: 'theme',
            value: 'dark',
            domain: '.app.roost.ai',
            path: '/',
            expires: 1728301475.2958,
            secure: true,
            httpOnly: false,
            sameSite: 'Strict'
          }
        ]
      }
    });
  });

  test.afterAll(async () => {
    await context.close();
  });

  test('Navigate and interact with Roost.ai login and subsequent pages', async () => {
    const page = await context.newPage();

    // Navigate to the login page
    await page.goto('/login');
    await expect(page).toHaveURL('/login');

    // Validate the presence of elements on the login page
    await expect(page.locator('text=Login')).toBeVisible();
    await expect(page.locator('text=RoostGPT Config')).toBeVisible();
    await expect(page.locator('text=My Cluster')).toBeVisible();
    await expect(page.locator('.login-logo')).toBeVisible();

    // Simulate user navigation via OAuth
    await page.click('a.google');

    // Validate the presence of elements after OAuth and navigation
    await expect(page).toHaveURL(new RegExp('/login#access_token=.+'));
    await expect(page.locator('.rotate-loading-icon')).toBeVisible();

    // Navigate to the tests page
    await page.goto('/roostgpt/tests');
    await expect(page).toHaveURL('/roostgpt/tests');
    await expect(page.locator('.loader_loaderBlueSpinner__Ip5Jt')).toBeVisible();

    // Navigate to the add test page
    await page.goto('/roostgpt/addTest');
    await expect(page).toHaveURL('/roostgpt/addTest');

    // Validate the presence of elements on the add test page
    await expect(page.locator('text=Test')).toBeVisible();
    await expect(page.locator('text=Gen AI Models')).toBeVisible();
    await expect(page.locator('text=Code Repositories')).toBeVisible();
    await expect(page.locator('text=Integration')).toBeVisible();
    await expect(page.locator('text=Advanced')).toBeVisible();
    await expect(page.locator('text=Notifications')).toBeVisible();

    // Fill in the test form based on the user interactions
    await page.fill('data-testid=test-name-input', 'Sample Test');
    await page.selectOption('data-testid=test-type-dropdown', 'Unit Test');
    await page.selectOption('data-testid=test-code-language-dropdown', 'Java');
    await page.selectOption('data-testid=test-framework-dropdown', 'JUnit5');
    await page.selectOption('data-testid=language-version', 'Java SE 17 (Default)');
    await page.selectOption('data-testid=java-build-tool', 'Maven');
    await page.selectOption('data-testid=maven-version', 'Maven 3.8.6 (Default)');

    // Save the test
    await page.click('data-testid=save-test-button');

    // Close the page after the test
    await page.close();
  });
});
